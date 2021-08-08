package main

import (
	"flag"
	"github.com/dawguy/rowing/website/server"
)

func main() {
	port := flag.String("port", "3000", "The port used by the server.")
	flag.Parse()
	s := server.NewServer(
		server.WithPort(*port),
	)

	s.Start()
}
