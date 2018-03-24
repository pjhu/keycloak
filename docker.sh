# run migrations
docker run --rm \
    --network=keycloak_keycloak \
    --link="keycloak-postgres" \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=postgres" \
    -e "KONG_PG_DATABASE=kong" \
    -e "KONG_PG_USER=keycloak" \
    -e "KONG_PG_PASSWORD=123456" \
    kong:0.12.3 kong migrations up --vv
    
# run kong
docker run --rm \
    --network=keycloak_keycloak \
    --link="keycloak-postgres" \
    -e "KONG_DATABASE=postgres" \
    -e "KONG_PG_HOST=postgres" \
    -e "KONG_PG_DATABASE=kong" \
    -e "KONG_PG_USER=keycloak" \
    -e "KONG_PG_PASSWORD=123456" \
    -e "KONG_ADMIN_LISTEN=0.0.0.0:8001" \
    -e "KONG_ADMIN_LISTEN_SSL=0.0.0.0:8444" \
    -p 8000:8000 \
    -p 8443:8443 \
    -p 8001:8001 \
    -p 8444:8444 \
    kong:0.12.3
        
            