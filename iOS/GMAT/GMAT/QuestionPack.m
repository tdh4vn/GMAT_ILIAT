//
//  QuestionPack.m
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuestionPack.h"

@implementation QuestionPack

- (instancetype)initWithJson:(NSDictionary *)json;
{
    self = [super init];
    
    if (self) {
        _questionPackId = json[@"_id"][@"oid"];
        _availableTime = json[@"available_time"];
        
        _questionIds = [[NSMutableArray alloc]init];
        
        for (NSString *questionId in json[@"question_ids"]) {
            [_questionIds addObject:questionId];
        }
    }
    
    return self;
}

@end
