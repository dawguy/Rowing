package server

import (
	"github.com/gorilla/mux"
	"io"
	"log"
	"net/http"
)

type Server struct {
	Port string
	Addr string
}

type ServerOption func(*Server)

func WithPort(p string) ServerOption{
	return func(s *Server) {
		s.Port = p
	}
}

func WithAddr(a string) ServerOption{
	return func(s *Server) {
		s.Addr = a
	}
}

func NewServer(opts ...ServerOption) *Server {
	const (
		defaultPort = "3000"
		defaultAddr = "127.0.0.1"
	)
	s := &Server{
		Port: defaultPort,
		Addr: defaultAddr,
	}

	for _, opt := range opts {
		opt(s)
	}

	return s
}
func setupRoutes() *mux.Router {
	r := mux.NewRouter()
	r.HandleFunc("/home", HomeHandler)
	r.HandleFunc("/athlete/{key}", AthleteHandler)
	r.HandleFunc("/athletes", AthletesHandler)
	r.HandleFunc("/workout/water/{key}", WaterWorkoutHandler)
	r.HandleFunc("/workout/land/{key}", LandWorkoutHandler)
	r.HandleFunc("/workouts/water", WaterWorkoutsHandler)
	r.HandleFunc("/workouts/land", LandWorkoutsHandler)


	return r
}

func HomeHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Home")
}

func AthleteHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Athletes")
}

func AthletesHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Athletes")
}
func WaterWorkoutHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Water Workout")
}
func LandWorkoutHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Land Workout")
}

func WaterWorkoutsHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Water Workouts")
}
func LandWorkoutsHandler(w http.ResponseWriter, r *http.Request) {
	io.WriteString(w,"Land Workouts")
}


func (s Server) Start() {
	r := setupRoutes()

	srv := &http.Server{
		Handler: r,
		Addr: s.Addr + ":" + s.Port,
	}

	log.Fatal(srv.ListenAndServe())
}

