def accept_js_confirm
  page.evaluate_script 'window.original_confirm_function = window.confirm;'
  page.evaluate_script 'window.confirm = function(msg) { return true; }'
end

