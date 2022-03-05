function WSSHClient() {
}

WSSHClient.prototype._generateEndpoint = function () {
    let protocol;
    if (window.location.protocol === 'https:') {
        protocol = 'wss://';
    } else {
        protocol = 'ws://';
    }
    return protocol + '127.0.0.1:8000/webssh';
};

WSSHClient.prototype.connect = function (options) {
    const endpoint = this._generateEndpoint();

    if (window.WebSocket) {
        // 如果支持 websocket
        this._connection = new WebSocket(endpoint);
    } else {
        // 否则报错
        options.onError('WebSocket Not Supported');
        return;
    }

    this._connection.onopen = function () {
        options.onConnect();
    };

    this._connection.onmessage = function (evt) {
        const data = evt.data.toString();
        options.onData(data);
    };


    this._connection.onclose = function () {
        options.onClose();
    };
};

WSSHClient.prototype.send = function (data) {
    this._connection.send(JSON.stringify(data));
};

WSSHClient.prototype.sendConnectData = function (options) {
    //连接参数
    this._connection.send(JSON.stringify(options));
};

WSSHClient.prototype.sendCommandData = function (data) {
    //发送指令
    this._connection.send(JSON.stringify({"operate": "command", "command": data}))
};

const client = new WSSHClient();
