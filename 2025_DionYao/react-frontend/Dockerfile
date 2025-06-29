FROM node:23-alpine3.20 AS builder

# Setting up the work directory
WORKDIR /app

# Installing dependencies
COPY package*.json ./
RUN npm install

# Copying all the files in our project
COPY . .

# Building our application
RUN npm run build

FROM nginx:alpine

# Copying built assets from builder
COPY --from=builder /app/dist /usr/share/nginx/html

# Default nginx config
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]