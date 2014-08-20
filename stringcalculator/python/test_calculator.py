import unittest
from string_calculator import add


class TestStringCalculator(unittest.TestCase):
    def test_empty_string(self):
        self.assertEqual(0, add(""))

    def test_one_number(self):
        self.assertEqual(1, add("1"))

    def test_two_numbers(self):
        self.assertEqual(3, add("1,2"))

    def test_three_numbers(self):
        self.assertEqual(5, add("1,2,2"))

    def test_new_line_delimiter(self):
        self.assertEqual(6, add("1\n2,3"))

    def test_support_different_delimiter(self):
        self.assertEqual(3, add("//;\n1;2"))

    def test_catch_negative_numbers(self):
        with self.assertRaises(ValueError) as context:
            add("-1")
            self.assertEqual("Negative numbers not allowed: -1",
                             context.exception.message)

    def test_numbers_bigger_than_1000_not_allowed(self):
        self.assertEqual(2, add("2,1001"))

    def test_bigger_delimiters(self):
        self.assertEqual(6, add("//[***]\n1***2***3"))

    def test_multiple_delimiters(self):
        self.assertEqual(6, add("//[*][%]\n1*2%3"))
