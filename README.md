```
________        .__        __              __                 __   
\_____  \  __ __|__| ____ |  | __  _______/  |______ ________/  |_ 
 /  / \  \|  |  \  |/ ___\|  |/ / /  ___/\   __\__  \\_  __ \   __\
/   \_/.  \  |  /  \  \___|    <  \___ \  |  |  / __ \|  | \/|  |  
\_____\ \_/____/|__|\___  >__|_ \/____  > |__| (____  /__|   |__|  
       \__>             \/     \/     \/            \/             
  _________            .__                                         
 /   _____/____________|__| ____    ____                           
 \_____  \\____ \_  __ \  |/    \  / ___\                          
 /        \  |_> >  | \/  |   |  \/ /_/  >                         
/_______  /   __/|__|  |__|___|  /\___  /                          
        \/|__|                 \//_____/                           

```

<div align="center">

![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=1.9.0%2B&color=74031C&style=for-the-badge)
![Jdk](https://img.shields.io/static/v1?label=Jdk&message=17++%2B&color=74031C&style=for-the-badge)
![Spring Boot](https://img.shields.io/static/v1?label=SpringBoot&message=3.1.1&color=74031C&style=for-the-badge)

</div>

# Table of Contents

- [Requirements](#requirements)
- [Running](#running)
- [Testing](#testing)
- [Deploy](#deploy)

# Requirements

- Docker
- OpenJDK 17+

# Running

To startup the containers run:

`docker compose up -d`

Migrations are executed automatically when the service starts.

To run service, execute:
`./gradlew bootRun` or only `./gradlew run`

The service may take a while to start, so be patient.

When it's up, the service will be available at `http://localhost:8080` and the kafka akhq
at http://localhost:8000/ui/local/topic.

# Testing

To run the tests, execute:
`./gradlew test`

# Deploy

> TODO
