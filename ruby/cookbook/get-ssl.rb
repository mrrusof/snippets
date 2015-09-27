#!/usr/bin/env ruby

require 'net/http'

uri = URI.parse('https://www.donotcall.gov/')
request = Net::HTTP.new(uri.host, uri.port)
# response = request.get('/')

require 'net/https'
request.use_ssl = true
request.verify_mode = OpenSSL::SSL::VERIFY_PEER
response = request.get('/')
puts response.body.size
