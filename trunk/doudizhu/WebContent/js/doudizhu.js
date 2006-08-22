var Actor = Class.create();
Actor.prototype = {
	initialize: function(isActor,sessionKey,keyHashCode,name,actorType,status,ip,actorIndex) {
		this.isActor = isActor;
		this.sessionKey = sessionKey;
		this.keyHashCode = keyHashCode;
		this.name = name;
		this.actorType = actorType;
		this.status = status;
		this.ip = ip;
		this.actorIndex = actorIndex;
	}
}


var Card = Class.create();
Card.prototype = {
	initialize: function(value,flag) {
		this.value = value;
		this.flag = flag;
	}
}