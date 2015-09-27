#!/usr/bin/env ruby

require 'net/http'
require 'net/https'
require 'uri'

module Net
  class HTTP
    def HTTP.get_with_headers(uri, headers=nil, opt=nil)
      uri = URI.parse(uri) if uri.is_a? String
      opt = { :use_ssl => true } if uri.port == 443 and opt == nil
      start(uri.host, uri.port, opt) do |http|
        return http.get(uri.path, headers)
      end
    end
  end
end

uncompressed = Net::HTTP.get_with_headers('https://www.donotcall.gov/')
puts "uncompressed.body.size = #{uncompressed.body.size}"

compressed = Net::HTTP.get_with_headers('https://www.donotcall.gov/', {'Accept-Encoding' => 'gzip'})
puts "compressed['Content-Encoding'] = #{compressed['Content-Encoding']}"
puts "compressed.body.size = #{compressed.body.size}"
