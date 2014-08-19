package main

import "fmt"
import "strconv"
import "regexp"
import "strings"

const (
	MAX                = 1000
	DEFAULT_DELIMITERS = ",|\n"
)

func Add(numbers string) (sum int) {
	if numbers == "" {
		return
	}
	delimiters := DEFAULT_DELIMITERS
	if regexp.MustCompile("//").MatchString(numbers) {
		delimiters = regexp.MustCompile("//(.*?)").FindStringSubmatch(numbers)[1]
		numbers = strings.Join(strings.Split(numbers, "\n")[1:], "\n")
	}
	for _, n := range regexp.MustCompile(delimiters).Split(numbers, -1) {
		i, _ := strconv.Atoi(n)
		if i <= MAX {
			sum += i
		}
	}
	return
}

func Run(numbers string, expected int) {
	if result := Add(numbers); result != expected {
		fmt.Printf("'%s', exptected %d but was %d\n", numbers, expected, result)
	} else {
		fmt.Println("OK")
	}
}

func main() {
	Run("", 0)
	Run("1", 1)
	Run("2", 2)
	Run("1,2", 3)
	Run("1,2,100", 103)
	Run("1,2\n3", 6)
	Run("1\n2,3\n4", 10)
	Run("//;\n10;20", 30)
	//Run("2,1001", 2)
	//Run("//[***]\n11***22***33", 66)
}
