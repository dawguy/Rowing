package server

import (
	"log"
	"net/http"
)

type Server struct {
	Port string

}

type ServerOption func(*Server)

func WithPort(p string) ServerOption{
	return func(s *Server) {
		s.Port = p
	}
}

func NewServer(opts ...ServerOption) *Server {
	const (
		defaultPort = "3000"
	)
	s := &Server{
		Port: defaultPort,
	}

	for _, opt := range opts {
		opt(s)
	}

	return s
}

func (s Server) Start() {
	mux := http.NewServeMux()
	//mux.HandleFunc()

	log.Fatal(http.ListenAndServe(s.Port, mux))
}

