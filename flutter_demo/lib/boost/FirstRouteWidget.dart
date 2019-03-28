import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

class FirstRouteWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        title: "第一个flutter页面",
        home: new Scaffold(
          appBar: new AppBar(
            title: new Text("第一个flutter页面"),
          ),
          body: FirstRoute(),
        ));
  }
}

class FirstRoute extends StatefulWidget {
  @override
  State createState() {
    return FirstRouteState();
  }
}

class FirstRouteState extends State<FirstRoute> {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text("Activity包装的flutter页面",
            style: new TextStyle(fontSize: 30, color: Colors.black)),
        new Container(
          margin: EdgeInsets.all(30),
          child: new Column(
            children: <Widget>[
              new RaisedButton(
                  onPressed: () {
                    FlutterBoost.singleton.closePageForContext(context);
                  },
                  child: new Text("关闭当前页面")),
              new RaisedButton(
                  onPressed: () {
                    FlutterBoost.singleton
                        .openPage("start_second_boost", {}, animated: true);
                  },
                  child: new Text("跳转到Second Boost页面"))
            ],
          ),
        ),
      ],
    );
  }
}
