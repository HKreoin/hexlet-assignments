# frozen_string_literal: true

require_relative 'test_helper'
require_relative '../lib/stack'

class StackTest < Minitest::Test
  # BEGIN
  def test_add()
    stack = Stack.new
    stack.push! 'one'
    stack.push! 'two'
    assert ['one', 'two'] == stack.to_a
  end

  def test_delete()
    stack = Stack.new
    stack.push! 'one'
    stack.push! 'two'
    stack.pop!
    assert ['one'] == stack.to_a
  end

  def test_clear()
    stack = Stack.new
    stack.push! 'one'
    stack.push! 'two'
    stack.clear!
    assert stack.to_a.empty?
  end

  def test_empty?()
    stack = Stack.new
    assert stack.empty?
  end
  # END
end

test_methods = StackTest.new({}).methods.select { |method| method.start_with? 'test_' }
raise 'StackTest has not tests!' if test_methods.empty?
