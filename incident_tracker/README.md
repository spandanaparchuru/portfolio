# Incident Tracker (Ruby on Rails)

A modern Incident Management application built using **Ruby on Rails 8**.

This application allows users to create, manage, update, filter, and track incidents with a clean dashboard UI and structured workflow.

---

## Features

- Create, Read, Update, Delete (CRUD)
- Open / InProgress / Resolved status tracking
- Notes field for progress updates
- Bulk delete functionality
- Status-based filtering (dropdown filter)
- Professional dashboard UI
- Timezone configured (Central Time)
- Responsive card-based layout
- Clean folder structure for portfolio

---

## UI Highlights

- Blue dashboard layout
- Card-based incident display
- Clickable incident cards
- Styled form pages (Create / Edit)
- Color-coded status badges
- Filter dropdown for incident status

---

## Tech Stack

- Ruby 3.4
- Rails 8.1
- SQLite (development)
- Bootstrap 5 (CDN)
- Custom CSS styling
- Turbo (default Rails 8)

---

## Project Structure


incident_tracker/
app/
config/
db/
app/views/incidents/
app/controllers/incidents_controller.rb


---

## Setup & Run Locally

Clone the repository:

```bash
git clone https://github.com/spandanaparchuru/portfolio.git
cd portfolio/incident_tracker

## Install dependencies:

bundle install

## Run database migrations:

rails db:migrate

## Start the server:

rails server

Visit: http://localhost:3000/incidents

## Key Learning Outcomes

- Rails MVC structure

- RESTful routing

- Custom controller actions (bulk_delete)

- Strong parameters

- View partials and reusable forms

- UI styling and layout organization

- Git & GitHub project structuring

- Handling timezones in Rails

- Debugging controller routing issues

## Future Improvements

- User authentication (Devise)

- Role-based access control

- Pagination

- Search functionality

- Incident activity timeline

- API endpoints

- Dockerization

- Production deployment (Render / Fly.io)

## Author

Spandana Parchuru

GitHub: https://github.com/spandanaparchuru

Portfolio Repository: https://github.com/spandanaparchuru/portfolio

License

This project is built for learning and portfolio demonstration purposes.