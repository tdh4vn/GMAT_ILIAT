//
//  QuestionPack.h
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QuestionPack : NSObject

@property (nonatomic, strong) NSString *packId;
@property (nonatomic, strong) NSString *availableTime;
@property (nonatomic, strong) NSArray *questionIds;

- (instancetype)initWithJson:(NSDictionary *)jsonDict;

@end
