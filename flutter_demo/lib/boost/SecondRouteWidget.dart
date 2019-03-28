import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

class SecondRouteWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(title: "second page", home: SecondRoute());
  }
}

class SecondRoute extends StatefulWidget {
  @override
  State createState() {
    return SecondRouteState();
  }
}

class SecondRouteState extends State<SecondRoute> {
  @override
  Widget build(BuildContext context) {
    return new Container(
        color: Colors.white,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              "Fragment包装的flutter页面",
              style: new TextStyle(
                  fontSize: 30,
                  color: Colors.black,
                  decoration: TextDecoration.none),
            ),
            new Container(
              margin: EdgeInsets.only(left: 0, top: 30, right: 0, bottom: 30),
              child: new Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  new RaisedButton(
                      onPressed: () {
                        FlutterBoost.singleton.closePageForContext(context);
                      },
                      child: new Text("关闭当前页面")),
                  new RaisedButton(
                      onPressed: () {
                        FlutterBoost.singleton
                            .openPage("start_first_boost", {}, animated: true);
                      },
                      child: new Text("跳转到First boost页面"))
                ],
              ),
            )
          ],
        ));
  }
}
