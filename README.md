# Coinminner App

## Prerequisites
Have Docker installed


## Launching the app
In an open terminal, enter the following commands one after another

  git clone https://github.com/Montichiari/Coinminner-App/
  cd Coinminner-App
  docker compose up --build


This will:
Build both the backend (Dropwizard) and frontend (React) containers
Start both services
Bind ports:
  Frontend: http://localhost
  Backend: http://localhost:8080
