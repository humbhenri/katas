package stringcalculator

import "testing"

func Test(t *testing.T) {
	AssertEqual(t, 0, Add(""))
	AssertEqual(t, 1, Add("1"))
	AssertEqual(t, 2, Add("2"))
	AssertEqual(t, 3, Add("1,2"))
	AssertEqual(t, 103, Add("1,2,100"))
	AssertEqual(t, 66, Add("11,22\n33"))
	AssertEqual(t, 10, Add("1\n2,3\n4"))
	AssertEqual(t, 30, Add("//;\n10;20"))
	AssertEqual(t, 60, Add("//***\n10***20***30"))
	AssertEqual(t, 70, Add("//[***]\n10***20***40"))
}

func AssertEqual(t *testing.T, expected interface{}, actual interface{}) {
	if expected != actual {
		t.Errorf("expected %v but got %v\n", expected, actual)
	}
}
