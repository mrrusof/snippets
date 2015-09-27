#!/usr/bin/env ruby

# with open-uri library
require 'open-uri'
puts "response via open-uri:"
puts open('http://www.oreilly.com/about/').read(200)

# with net/http library
require 'net/http'
response = Net::HTTP.get_response('www.oreilly.com', '/about/')
puts "response.code = #{response.code}"
puts "response.body.size = #{response.body.size}"
puts "response['Content-type'] = #{response['Content-type']}"
puts "response.body[0,200] =\n#{response.body[0,200]}"
puts "Success!" if response.is_a? Net::HTTPOK

response = Net::HTTP.get_response(URI('http://www.oreily.com/abouty'))
puts case response.code[0]
     when ?1 then 'Status code indicates an HTTP informational response.'
     when ?2 then 'Status code indicates success.'
     when ?3 then 'Status code indicates redirection.'
     when ?4 then 'Status code indicates client error.'
     when ?5 then 'Status code indicates server error.'
     else 'Non-standard status code.'
     end
puts "response['sErVer'] = #{response['sErVer']}"
puts "response.each_key { |key| puts key } = #{response.each_key { |key| puts key }}"

Net::HTTP.get_response('www.oreilly.com', '/about/') do |response|
  response.read_body do |segment|
    puts "Received segment of #{segment.size} byte(s)!"
  end
end
