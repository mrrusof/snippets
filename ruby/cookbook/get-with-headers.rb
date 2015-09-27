#!/usr/bin/env ruby

require 'net/http'
require 'uri'

module Net
  class HTTP
    def HTTP.get_with_headers(uri, headers=nil)
      uri = URI.parse(uri) if uri.respond_to? :to_str
      start(uri.host, uri.port) do |http|
        return http.get(uri.path, headers)
      end
    end
  end
end

res = Net::HTTP.get_with_headers('http://www.google.com/',
                                 {'Accept-Language' => 'de'})
case res
when Net::HTTPSuccess
  puts res.body
when Net::HTTPRedirection
  puts "Redirecting to #{res['location']}"
  res = Net::HTTP.get_with_headers(res['location'], {'Accept-Language' => 'de'})
  puts res.body
end
