#!/usr/bin/env ruby

require 'gserver'

class PsCgiServer < GServer
  def initialize(port=2060)
    super(port, GServer::DEFAULT_HOST, Float::MAX, $stderr, true)
  end

  def serve(s)
    puts 'Responding with output of ps-cgi.rb'
    s.puts <<-end
HTTP-1.0 200 OK
#{`REQUEST_METHOD=GET ./ps-cgi.rb`}
end
  end
end

server = PsCgiServer.new(ARGV[1] || 2060)
server.start(-1)
begin
  server.join
rescue Interrupt => e
  puts 'bye bye!'
end
