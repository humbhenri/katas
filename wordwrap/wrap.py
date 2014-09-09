#!/bin/env python


def wrap(text, col):
    if len(text) <= col:
        return text

    cut1, cut2 = col, col
    space = text[:col].rfind(' ')
    if space != -1:
        cut1, cut2 = space, space+1
    return text[:cut1] + '\n' + wrap(text[cut2:], col)


if __name__ == '__main__':
    import sys
    print wrap(sys.stdin.read(), int(sys.argv[1]))
