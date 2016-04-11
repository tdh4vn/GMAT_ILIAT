//
//  QuestionPack.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuestionPack.h"

@implementation QuestionPack

- (instancetype)initWithJson:(NSDictionary *)jsonDict;
{
    self = [super init];
    
    if (self) {
        
        self.packId = jsonDict[@"_id"][@"oid"];
        self.availableTime = jsonDict[@"available_time"];
        self.questionIds = jsonDict[@"question_ids"];
    }
    
    return self;
}

@end
