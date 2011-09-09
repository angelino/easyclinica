rm -rf target
ant only-war
cd target
rm -rf temp
mkdir temp
cd temp
cp ../easyclinica.war .
unzip easyclinica.war
rm easyclinica.war
cd ../..
rm files.txt
ssh root@50.57.116.135 'ruby deploy-scripts/list-current.rb /var/www/app/htdocs/' > files.txt
ruby diffs.rb target/temp files.txt > to_be_removed.txt
cd target/temp
zip -r diffs *

scp diffs.zip root@50.57.116.135:/var/www/app
scp ../../to_be_removed.txt root@50.57.116.135:/var/www/app
ssh root@50.57.116.135 'sh deploy-scripts/diff-server-deploy.sh'

cd ..
rm -rf temp
cd ..
rm to_be_removed.txt
rm files.txt
