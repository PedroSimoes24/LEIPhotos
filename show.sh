#!/bin/bash

# Obtém o endereço IP da interface de rede vEthernet (WSL)
ip_address=$(ip addr show dev eth0 | awk '$1 == "inet" {print $2}' | cut -d'/' -f1)

# Adiciona o endereço IP ao Xauthority usando xauth
xauth add "$ip_address:0" . "$(wsl whoami)"
