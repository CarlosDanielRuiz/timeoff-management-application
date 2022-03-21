# -------------------------------------------------------------------
# Minimal dockerfile from alpine base
#
# Instructions:
# =============
# 1. Create an empty directory and copy this file into it.
#
# 2. Create image with: 
#	docker build --tag timeoff:latest .
#
# 3. Run with: 
#	docker run -d -p 3000:3000 --name alpine_timeoff timeoff
#
# 4. Login to running container (to update config (vi config/app.json): 
#	docker exec -ti --user root alpine_timeoff /bin/sh
# --------------------------------------------------------------------

# Alpine Image with Log4Shell CVE not detected
FROM alpine:3.13.8 

EXPOSE 3000

LABEL org.label-schema.schema-version="1.0"
LABEL org.label-schema.docker.cmd="docker run -d -p 3000:3000 --name alpine_timeoff"

RUN apk add --update --no-cache \
    make \
    nodejs \
    npm \
    python3 \
    vim

# you'll likely want the latest npm, regardless of node version, for speed and fixes
# but pin this version for the best stability
RUN npm i npm@latest -g

RUN adduser --system timeoff-app --home /app
USER timeoff-app
WORKDIR /app/timeoff-management
COPY --chown=timeoff-app:timeoff-app . .

RUN npm install --no-optional && npm cache clean --force

CMD npm start
