package router

import (
	"net/http"
	"strings"
)

var splitPaths []splitPath
var fallback Path

type Handler func(w http.ResponseWriter, r *http.Request)

type Path struct {
	Method string
	Url string
	Handler Handler
}

type splitPath struct {
	pieces []string
	path Path
}

func GenerateHandlers(paths []Path) []splitPath{
	splitPaths = make([]splitPath, 0)

	for _, p := range paths {
		s := strings.Split(p.Url, "/")
		if s[0] == "" {
			s = s[1:]
		}
		splitPaths = append(splitPaths, splitPath{s,  p})
	}

	if len(splitPaths) > 0 {
		fallback = splitPaths[0].path
	} else {
		fallback = Path{"GET","/", func(w http.ResponseWriter, r *http.Request){}}
	}

	return splitPaths
}

func GetHandler(url string, method string) Handler{
	splitUrl := strings.Split(url, "/")
	if splitUrl[0] == "" {
		splitUrl = splitUrl[1:]
	}

	methodPaths := make([]splitPath, 0)

	for _, path := range splitPaths {
		if path.path.Method == method {
			methodPaths = append(methodPaths, path)
		}
	}

	var bestChoice splitPath
	for _, path := range methodPaths {
		match := compare(path.pieces, splitUrl)

		if match {
			bestChoice = path
			break
		}
	}

	if bestChoice.path.Handler == nil {
		return fallback.Handler

	}

	return bestChoice.path.Handler
}

func compare(path []string, url []string) bool {
	if len(path) != len(url) {
		return false
	}

	for i, _ := range path {
		if path[i] != url[i] {
			return false
		}
	}

	return true
}