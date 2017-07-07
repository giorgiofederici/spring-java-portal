#!/bin/sh
# Call Jenkins to start build and pass environment parameter
#
echo "executing post-receive hook"
echo "environment=testing"
echo "user=jenkins"

# cURL POST request using jenkins user with API token
curl -u jenkins:99a231f796136bbc3728a44695f383e2 \
    --data "delay=0sec&environment=testing" \
    "http://springjavaportal-gfederici.rhcloud.com/jenkins/job/GitMavenTomcat_Build/buildWithParameters"
