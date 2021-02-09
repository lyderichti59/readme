#!/usr/bin/env bash

set -x;

ROOT_DIR=$(dirname $(dirname $(realpath $0)));
source "$ROOT_DIR/.env.local";

# Variable that should be set :
# SSH_PORT, SSH_USER, SSH_TARGET, SSH_DIRECTORY

rsync --recursive \
      --links \
      --partial --progress \
      --verbose \
      -e "ssh -p ${SSH_PORT}" \
      --rsync-path=/usr/bin/rsync \
      --delete \
      --info=progress0,stats \
      public/_firn/_site/* \
      ${SSH_USER}@${SSH_TARGET}:${SSH_DIRECTORY};
