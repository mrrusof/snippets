#!/usr/bin/env ruby

processes = %x{ps aux}.split(/\r?\n/).collect do |proc|
  '<tr><td>' + proc.split(/\s+/, 11).join('</td><td>') + '</td></tr>'
end

title = %{Processes running on #{ENV['SERVER_NAME'] || `hostname`.strip}}

puts <<-end
Content-Encoding: text/html

<HTML>
  <HEAD><TITLE>#{title}</TITLE></HEAD>
  <BODY>
    <H1>#{title}</H1>
    <TABLE>
      #{processes.join("\n")}
    </TABLE>
  </BODY>
</HTML>
end

exit 0
