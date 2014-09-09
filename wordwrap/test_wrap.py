import unittest
from wrap import wrap


class TestWW(unittest.TestCase):
    def test_empty_string(self):
        self.assertEqual('', wrap('', 1))

    def test_string_smaller_than_col(self):
        self.assertEqual('ab', wrap('ab', 3))

    def test_string_without_spaces(self):
        self.assertEqual('ab\ncd', wrap('abcd', 2))

    def test_big_string_without_spaces(self):
        self.assertEqual('ab\ncd\nef\ngh', wrap('abcdefgh', 2))

    def test_string_with_space_at_column(self):
        self.assertEqual('word\nword', wrap('word word', 5))

    def test_after_word_boundary(self):
        self.assertEqual('word\nword', wrap('word word', 6))

    def test_three_words_after_first_space(self):
        self.assertEqual('word\nword\nword', wrap('word word word', 6))

    def test_three_words_after_second_space(self):
        self.assertEqual('word word\nword', wrap('word word word', 10))
