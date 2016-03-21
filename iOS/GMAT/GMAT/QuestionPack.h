//
//  QuestionPack.h
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QuestionPack : NSObject

@property(nonatomic,strong) NSString *questionPackId;
@property(nonatomic,strong) NSString *availableTime;
@property(nonatomic,strong) NSMutableArray *questionIds;

- (instancetype)initWithJson:(NSDictionary *)json;

@end
