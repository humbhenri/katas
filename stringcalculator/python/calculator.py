#!/usr/bin/env python
import re


def add(expression):
    if expression == "":
        return 0
    numbers = parse_numbers(expression)
    numbers = [n for n in numbers if n <= 1000]
    ensure_all_non_negative(numbers)
    return sum(numbers)


def ensure_all_non_negative(numbers):
    negatives = [n for n in numbers if n < 0]
    if negatives:
        raise ValueError("Negative numbers not allowed: " +
                         " ".join(map(str, negatives)))


def parse_numbers(expression):
    if expression.startswith("//"):
        numbers = split_by_custom_delimiters(expression)
    else:
        numbers = split_by_newline_and_commas(expression)
    return map(int, numbers)


def split_by_custom_delimiters(expression):
    m = re.search("//(.*)\n(.*)", expression)
    delimiters = extract_custom_delimiters(m.group(1))
    numbers = re.split(delimiters, m.group(2))
    return numbers


def extract_custom_delimiters(expression):
    delimiters = re.split('\[|\]', expression)
    delimiters = [re.escape(d) for d in delimiters if d != '']
    return '|'.join(delimiters)


def split_by_newline_and_commas(expression):
    delimiters = ",|\n"
    return re.split(delimiters, expression)
