#!/usr/bin/env ruby

require 'resolv'

dns = Resolv::DNS.new
domain = 'oreilly.com'

puts "Email servers at #{domain}"
dns.each_resource(domain, Resolv::DNS::Resource::IN::MX) do |mail_server|
  puts mail_server.exchange
end

puts "\nDNS servers at #{domain}"
dns.each_resource(domain, Resolv::DNS::Resource::IN::NS) do |nameserver|
  puts nameserver.name
end
