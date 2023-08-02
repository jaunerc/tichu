# Tichu app

## Technical decisions
### Frontend
* The frontend is implemented with an Angular app.
* Each page has its own module
* Each page module is in the router configured to be lazy-loaded.

### Backend
* The backend provides an api which is declared with an OpenApi spec.
* The REST controllers and dto classes are generated from the spec.
