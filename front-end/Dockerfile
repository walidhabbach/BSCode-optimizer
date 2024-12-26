# Use an official Node.js runtime as a parent image
FROM node:20.12.1-alpine

WORKDIR /app

# Copy package.json and package-lock.json files to the working directory
COPY package.json ./
COPY package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application code to the working directory
COPY . .

# Expose the port that Vite will run on
EXPOSE 5173

# Command to run your app using Vite's development server
CMD ["npm", "run", "dev", "--", "--host"]
