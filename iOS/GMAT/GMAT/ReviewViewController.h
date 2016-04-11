//
//  ReviewViewController.h
//  GMAT
//
//  Created by Trung Đức on 4/11/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Question.h"
#import "StudentAnswer.h"

@interface ReviewViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) StudentAnswer *studentAnswer;
@property (nonatomic, strong) Question *question;
@property (weak, nonatomic) IBOutlet UITableView *tbvReview;

@end
