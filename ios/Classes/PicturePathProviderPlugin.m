#import "PicturePathProviderPlugin.h"
#if __has_include(<picture_path_provider/picture_path_provider-Swift.h>)
#import <picture_path_provider/picture_path_provider-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "picture_path_provider-Swift.h"
#endif

@implementation PicturePathProviderPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPicturePathProviderPlugin registerWithRegistrar:registrar];
}
@end
