package stringcalculator

import "strconv"
import "regexp"
import "strings"

const (
	max               = 1000
	defaultDelimiters = ",|\n"
)

func Add(numbers string) int {
	if numbers == "" {
		return 0
	}
	delimiters := defaultDelimiters
	if hasCustomDelimiter(numbers) {
		delimiters = findDelimiters(numbers)
		numbers = removeFirstLine(numbers, delimiters)
	}
	return sum(split(numbers, delimiters))

}

func hasCustomDelimiter(numbers string) bool {
	return regexp.MustCompile("//").MatchString(numbers)
}

func findDelimiters(numbers string) string {
	return regexp.MustCompile("//(.*?)\n").FindStringSubmatch(numbers)[1]
}

func removeFirstLine(numbers string, delimiters string) string {
	return strings.Join(strings.Split(numbers, "\n")[1:], "\n")
}

func split(numbers string, delimiters string) []string {
	return regexp.MustCompile(delimiters).Split(numbers, -1)
}

func sum(numbers []string) int {
	sum := 0
	for _, n := range numbers {
		i, _ := strconv.Atoi(n)
		if i <= max {
			sum += i
		}
	}
	return sum
}
