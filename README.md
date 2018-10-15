# Sample Login App

This is a backend for https://github.com/dlopes7/appd-demo-router

## Routes

POST /api/balance

```javascript
{
	"branchNumber": "0123",
	"accountNumber": "456789"
}
```

POST /api/transfer

```javascript
{
	"to": {
		"branchNumber": "0123",
		"accountNumber": "456789"
	},
	"from": {
		"branchNumber": "3210",
		"accountNumber": "987654"
	},
	"amount": 500

}
```

Any operation will work, reponse time is between 300ms and 1s

## To run

Without docker:

- mvn springboot:run

With docker and AppDynamics:

1. `docker build . -t appd-demo-account`
2. `docker run -d -p 8082:8082 --name=account -v /opt/java-agent:/usr/java-agent -e "JAVA_OPTS=-Dserver.port=8082 -javaagent:/usr/java-agent/javaagent.jar -Dappdynamics.agent.applicationName=Mobile -Dappdynamics.agent.tierName=login -Dappdynamics.agent.reuse.nodeName=true -Dappdynamics.agent.reuse.nodeName.prefix=login -Dappdynamics.controller.port=8090 -Dappdynamics.controller.hostName=controller_hostname -Dappdynamics.agent.accountAccessKey=controller_access_key" appd-demo-account`
