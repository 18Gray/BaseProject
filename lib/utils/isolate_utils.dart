
import 'dart:isolate';


loadData() async {
  /// 通过spawn新建一个isolate，入参是dataLoader函数，和参数receivePort.sendPort
  ReceivePort receivePort = ReceivePort();
  SendPort sendPort = receivePort.sendPort;

  String url = 'https://jsonplaceholder.typicode.com/posts';
  await Isolate.spawn(dataLoader, [sendPort, url]);

  await receivePort.first;
}

// isolate的绑定方法
dataLoader(List<dynamic> args) async {
  SendPort responsePort = args[0];
  String url = args[1];

  // 网络操作

  responsePort.send("Http Get Data Success");
}

