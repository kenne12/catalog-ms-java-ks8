FROM node:22 AS build

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build:prod

FROM nginx:1.25
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
EXPOSE 80