package router

import (
	"fmt"
	"net/http"
	"reflect"
	"runtime"
	"testing"
)

func fallbackHandler(w http.ResponseWriter, r *http.Request){

}

func fallbackHandler2(w http.ResponseWriter, r *http.Request){

}

func setupPaths() []Path{
	var paths = []Path {
		Path {"GET","/home",fallbackHandler},
		Path {"GET","/home/abc",fallbackHandler2},
		Path {"GET","/home/edf",fallbackHandler},
		Path {"PUT","/home",fallbackHandler2},
		Path {"POST","/home",fallbackHandler2},
		Path {"POST","/home/abc",fallbackHandler},
	}

	return paths
}

func getFunctionName(i interface{}) string {
	// https://stackoverflow.com/questions/7052693/how-to-get-the-name-of-a-function-in-go
	return runtime.FuncForPC(reflect.ValueOf(i).Pointer()).Name()
}

func TestGenerateHandlers(t *testing.T) {
	paths := setupPaths()
	GenerateHandlers(paths)

	var lens = []int{1,2,2,1,1,2}

	for i, sp := range splitPaths {
		if len(sp.pieces) != lens[i] {
			t.Fatal(fmt.Sprintf("Failed test on %d : %v", len(sp.pieces), sp))
		}
	}
}

func TestGetHandler(t *testing.T) {
	paths := setupPaths()
	GenerateHandlers(paths)

	type testCase struct {
		url string
		method string
		expectedHandler Handler
	}

	handlers := []testCase{
		testCase{"/home","GET", fallbackHandler},
		testCase{"/home", "POST", fallbackHandler2},
		testCase{"/home/abc", "GET", fallbackHandler2},
		testCase{"/home/edf", "GET", fallbackHandler},
		testCase{"/DoesNotExistInMap", "GET", fallbackHandler},
	}

	for _, h := range handlers {
		handler := GetHandler(h.url, h.method)

		if getFunctionName(handler) != getFunctionName(h.expectedHandler) {
			t.Fatal(fmt.Sprintf("Failed to match handlers %v -- %v -- %v\n", h.url, h.expectedHandler, handler))
		}
	}
}