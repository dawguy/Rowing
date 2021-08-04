package main

import (
	"fmt"
	"github.com/dawguy/rowing/website/common"
	"time"
)

func main() {
	athlete := common.Athlete{
		1,
		"David",
		time.Now(),
	}

	fmt.Printf("%s", athlete)
}
