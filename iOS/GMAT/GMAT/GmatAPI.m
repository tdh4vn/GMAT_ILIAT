//
//  GmatAPI.m
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "GmatAPI.h"
#import "AFNetworking/AFNetworking.h"
#import "Constant.h"
#import "Question.h"


@interface GmatAPI ()

@property NSURLSessionConfiguration *configuration;
@property AFHTTPSessionManager *httpSessionManager;

@end


@implementation GmatAPI

+ (instancetype)sharedInstance;
{
    static dispatch_once_t onceToken;
    static GmatAPI *sharedInstance;
    
    dispatch_once(&onceToken, ^{
        sharedInstance = [[GmatAPI alloc]init];
    });
    
    return sharedInstance;
    
}

- (instancetype)init;
{
    self = [super init];
    
    if (self) {
        
        _configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
        
        _httpSessionManager = [[AFHTTPSessionManager alloc]initWithSessionConfiguration:_configuration];
        
        _httpSessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"text/plain", @"text/html", nil];

        
    }
    
    return self;
}


- (void)exploreQuestionWithCompletionBlock:(void(^)(NSArray *questions))completion;
{
    
    
    NSURLSessionDataTask *dataTask = [_httpSessionManager GET:kGmatAPIExploreUrl
                                                   parameters:nil
                                                     progress:nil
                                                      success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
                                                          
                                                          if (completion && responseObject) {
                                                              completion(responseObject);
                                                          }
                                                        
                                                      } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
                                                          
                                                          if (error) {
                                                              NSLog(@"error: %@",error);
                                                          }
                                                          
                                                      }];
//
    [dataTask resume];
    
}

@end
