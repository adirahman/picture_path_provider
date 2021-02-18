import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:picture_path_provider/picture_path_provider.dart';

void main() {
  const MethodChannel channel = MethodChannel('picture_path_provider');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await PicturePathProvider.platformVersion, '42');
  });
}
