#!/usr/bin/env ruby

# puts <<-end
# Content-Type: text/html

# <html>
#   <head>
#     <title>Hola mundo!</title>
#   </head>
#   <body>
#     <h1>Hola mundo!</h1>
#   </body>
# </html>
# end

require 'cgi'

cgi = CGI.new('html3')
processes = `ps aux`.split(/\r?\n/).collect { |proc| proc.split /\s+/, 11 }
title = %{Processes running on #{ENV['SERVER_NAME'] || %x{hostname}.strip}}

cgi.out do
  cgi.html do
    cgi.head { cgi.title { title } } + cgi.body do
      cgi.h1 { title } +
      cgi.table do
        (processes.collect do |fields|
            cgi.tr { fields.collect { |field| cgi.td { field } }.join " " }
          end).join "\n"
      end
    end
  end
end

exit 0
