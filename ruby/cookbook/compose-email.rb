#!/usr/bin/env ruby

gem 'actionmailer'
require 'action_mailer'

class SimpleMailer < ActionMailer::Base
  default from: 'ruslan@example.org'

  def simple_message(recipient)
    mail(from: 'ruslan@example.org',
         to: recipient,
         subject: 'Hola!',
         body: 'Wanna code?'
         )
  end
end

class ActionMailer::Base
  def simple_message(recipient)
    mail(from: 'ruslan@example.org',
         to: recipient,
         subject: 'Hola!',
         body: 'Wanna code?')
  end
end

puts 'Compose message with SimpleMailer:'
puts SimpleMailer.simple_message('ruslan@example.org').to_s

puts "\nCompose message with extended ActionMailer::Base"
puts ActionMailer::Base.simple_message('ruslan@example.org').to_s

