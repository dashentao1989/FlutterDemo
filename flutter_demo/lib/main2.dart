import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(FlutterApp(routerName: window.defaultRouteName));

/// Flutter页面
class FlutterApp extends StatelessWidget {
  String _routerName;

  FlutterApp({String routerName, Key key}) : super(key: key) {
    this._routerName = routerName;
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        title: "flutter页面",
        home: new Scaffold(
          appBar: new AppBar(title: new Text("flutter页面")),
          body: _widgetForRoute(_routerName),
        ));
  }
}

Widget _widgetForRoute(String route) {
  print("start");
  switch (route) {
    case 'route1':
      return new flutterView();
    case 'route2':
      return new flutterFragment();
    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}

class flutterView extends StatefulWidget {
  @override
  State createState() {
    return new flutterViewState();
  }
}

class flutterViewState extends State<flutterView> {
  String _name = "李四";
  static const MethodChannel platform =
      const MethodChannel('samples.flutter.io/flutter_view');
  static const EventChannel eventChannel =
      const EventChannel("samples.flutter.io/native_view");

  @override
  void initState() {
    super.initState();
    platform.setMethodCallHandler(platformCallHandler);
    eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  }

  Future<dynamic> platformCallHandler(MethodCall call) async {
    switch (call.method) {
      case "welcome":
        return "Hello from Flutter";
        break;
    }
  }

  void _onEvent(Object event) {
    setState(() {
      _name = event;
    });
  }

  void _onError(Object error) {}

  @override
  Widget build(BuildContext context) {
    return new Container(
        color: Colors.white,
        child: new FlatButton(
            onPressed: () async {
              String nativeName;
              try {
                final String result = await platform.invokeMethod('getName');
                nativeName = 'get flutter native name at $result % .';
              } on PlatformException catch (e) {
                nativeName = "not name";
              }

              setState(() {
                _name = nativeName;
              });
            },
            child: new Text(
              _name,
              textDirection: TextDirection.ltr,
              style: TextStyle(fontSize: 40),
            )));
  }
}

class flutterFragment extends StatefulWidget {
  @override
  State createState() {
    return new flutterFragmentState();
  }
}

class flutterFragmentState extends State<flutterFragment> {
  static const platform = const MethodChannel('samples.flutter.io/fragment');
  String _name = "李四";

  @override
  Widget build(BuildContext context) {
    return new Container(
        color: Colors.red,
        child: new FlatButton(
            onPressed: () async {
              String name;
              try {
                name = await platform.invokeMethod("getName");
              } catch (e) {
                print(e);
                name = "name is null";
              }

              setState(() {
                _name = name;
              });
            },
            child: new Text(
              _name,
              textDirection: TextDirection.ltr,
              style: new TextStyle(color: Colors.white, fontSize: 30),
            )));
  }
}
