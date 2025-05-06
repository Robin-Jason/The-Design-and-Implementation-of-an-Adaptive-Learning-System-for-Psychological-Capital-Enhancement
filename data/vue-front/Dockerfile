FROM openresty/openresty:alpine-fat
COPY ./dist /usr/local/openresty/nginx/html/dist
RUN true
COPY ./ngx.conf /usr/local/openresty/nginx/conf/nginx.conf
RUN true
EXPOSE 80
CMD ["/usr/local/openresty/bin/openresty", "-g", "daemon off;"]
