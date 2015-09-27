#!/usr/bin/env ruby

require 'gserver'

class PsServer < GServer
  def initialize(port=2060, host=GServer::DEFAULT_HOST)
    super(port, host, Float::MAX, $stderr, true)
  end

  def serve(sock)
    puts 'Responding with output of ps.rb'
    sock.puts 'HTTP-1.0 200 OK'
    sock.puts `./ps.rb`
  end
end

server = PsServer.new(*ARGV[0..2] || 2060)
server.start(-1)
begin
  server.join
rescue Interrupt => e
  puts 'bye bye!'
end
