require 'digest/md5'

def compare(new_dir, old_artifacts)

  Dir.glob(new_dir + '/*').each do |f|
    if File.directory? f
      compare(f, old_artifacts) 
      Dir.delete f if Dir[f+"/*"].empty?
    else
      found = find_artifact_with_name(f.gsub(ARGV[0], ''), old_artifacts)
      
      if(found != nil)
        if found.md5.to_s == Digest::MD5.hexdigest(File.read(f)).to_s
          File.delete(f)
        end
      end
      
      old_artifacts.delete(found)
    end
  end
  
end

def find_artifact_with_name(name, artifacts)
  artifacts.each do |a|
    return a if(a.name == name)
  end
  
  nil
end

def get_artifacts_from(file)
  artifacts = Array.new
  
  file = File.new(file, "r")
  while (line = file.gets)
    splitted_line = line.split(';')
    artifacts << Artifact.new(splitted_line[0], splitted_line[1].gsub("\n", ""))
  end
  file.close
  
  artifacts
end

class Artifact
  attr_accessor :name, :md5
  def initialize(name, md5)
    @name = name
    @md5 = md5
  end
end

old_artifacts = get_artifacts_from(ARGV[1])
compare(ARGV[0], old_artifacts)

old_artifacts.each do |x|
  puts x.name
end