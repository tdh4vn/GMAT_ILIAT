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
#import "MagicalRecord/MagicalRecord.h"
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


- (void)exploreQuestionWithCompletionBlock:(void(^)(NSArray *question))completion;
{
    
    [_httpSessionManager GET:kGmatAPIVersionUrl parameters:nil progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        
        if (completion && responseObject) {
            NSString *currentVersion = [[NSUserDefaults standardUserDefaults] objectForKey:@"version"];
            
            NSString *apiVersion = responseObject[@"value"];
            
            if (![currentVersion isEqualToString:apiVersion]) {
                
                [_httpSessionManager GET:kGmatAPIExploreQuestionUrl
                                         parameters:nil
                                           progress:nil
                                            success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
                                                        
                                                    [Question MR_truncateAll];
                                                        
                                                    for (NSDictionary *jsonDict in responseObject[@"questions"]) {
                                                        [Question createQuestionWithJson:jsonDict];
                                                    }
                                                        
                                                    [[NSManagedObjectContext MR_defaultContext]MR_saveToPersistentStoreWithCompletion:^(BOOL contextDidSave,NSError* error) {
                                                        
                                                    }];
                                                        
                                                    [[NSUserDefaults standardUserDefaults]setObject:apiVersion forKey:@"version"];
                                                
                                                    [[NSUserDefaults standardUserDefaults] synchronize];
                                                        
                                                    NSLog(@"%lu",(unsigned long)[Question MR_countOfEntities]);
                                                    
                                                    completion(responseObject[@"questions"]);
                                                
                                                
                                            } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
                                                
                                                if (error) {
                                                    NSLog(@"error: %@",error);
                                                }
                                                
                                            }];
        }
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        if (error) {
            NSLog(@"error: %@",error);
        }
    }];
    
}


- (void)exploreQuestionPackWithCompletionBlock:(void(^)(NSArray *questionPacks))completion;
{
    
    NSURLSessionDataTask *dataTask = [_httpSessionManager GET:kGmatAPIExploreQuestionPackUrl
                                                   parameters:nil
                                                     progress:nil
                                                      success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
                                                          
                                                          if (completion && responseObject) {
                                                              
                                                              completion(responseObject[@"question_packs"]);
                                                              
                                                          }
                                                          
                                                      } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
                                                          
                                                          if (error) {
                                                              NSLog(@"error: %@",error);
                                                          }
                                                          
                                                      }];
    
    [dataTask resume];
    
}

@end
