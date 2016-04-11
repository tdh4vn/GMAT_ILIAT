//
//  GmatAPI.h
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>

#define sGmatAPI    [GmatAPI sharedInstance]

@interface GmatAPI : NSObject

+ (instancetype)sharedInstance;

- (void)exploreQuestionWithCompletionBlock:(void(^)(NSArray *question))completion;

- (void)exploreQuestionPackWithCompletionBlock:(void(^)(NSArray *questionPacks))completion;

@end
